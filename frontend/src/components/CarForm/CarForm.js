import React, {useState} from 'react';
import  TextField  from '../TextField/TextField';
import RangeField from '../RangeField/RangeField';
import 'bootstrap/dist/css/bootstrap.min.css'
import Navbar from 'react-bootstrap/Navbar'
import './Form.css';
import axios from 'axios';
import * as Yup from 'yup';
import logo from './logo.png';
import SERVER_URL from "../../utils/Constants";

import {Formik,Form} from 'formik';
import { Container, Nav } from 'react-bootstrap';
import RadioYesNo from '../RadioYesNo/RadioYesNo';
import SelectVehicle from '../SelectVehicle/SelectVehicle';
import SelectEngineSize from '../SelectEngineSize/SelectEngineSize';
import RadioAdditionalDrivers from '../RadioAdditionalDrivers/RadioAdditionalDrivers';
import { DatePickerField } from '../DatePickerField/DatePickerField';
import QuoteModal from "../QuoteModal/QuoteModal";


function CarForm(){

  const date = new Date();

  const [modalShow,setModalShow] =React.useState(false);
  const [returnedQuote,setReturnedQuote] = useState();


  const quoteEndpoint = "http://localhost:8080/quotes";
  const testEndpoint = "https://6151d1934a5f22001701d46f.mockapi.io/api/capston"
  const prodEndpoint = `${SERVER_URL}/quotes`;

  const validate = Yup.object({
    prefix: Yup.string().required('Required'),
    firstName: Yup.string().required('Required'),
    lastName: Yup.string().required('Required'),
    addressLine1: Yup.string().required('Required'),
    addressLine2: Yup.string().required('Required'),
    city: Yup.string().required('Required'),
    vehicleType: Yup.string().required('Please select vehicle type.'),
    engineSize: Yup.string().required('Please select engine size.'),
    additionalDrivers: Yup.string().required('Required'),
    commercial: Yup.boolean().required('Required'),
    registeredState: Yup.boolean().required('Required'),
    currentValue: Yup.number().required('Required').positive().integer(),
    telephone: Yup.string().required('Required'),
    dateRegistered: Yup.date().required('Please Enter a Date.').max(date),
    postcode: Yup.string().required('Required')
  })
  
  return( 
<Formik initialValues= {{
  prefix:'',
  firstName:'',
  lastName:'',
  addressLine1:'',
  addressLine2:'',
  city:'',
  postcode:'',
  vehicleType:'',
  engineSize:'',
  additionalDrivers:'',
  commercial:false,
  registeredState:false,
  currentValue:25000,
  telephone:'',
  dateRegistered:'',
  quoteAmount:''
}}
  validationSchema={validate}
  onSubmit={async(values) =>{
    axios.post(quoteEndpoint, values)
         .then((r)=>{
           setReturnedQuote(r.data);
           setModalShow(true);
         })
         .catch((e)=>{
           alert(e)
         })
  }}
>
  {formik =>(
    <div>
      <Navbar bg="primary" variant="dark">
        <Container>
          <Navbar.Brand>
            <img src={logo} alt="Allstate" height="100"/>
          </Navbar.Brand>
          <Nav>
            <Nav.Link href="/">Form</Nav.Link>
            <Nav.Link href="/admin">Admin</Nav.Link>
          </Nav>
        </Container>
      </Navbar>
      <pre>{JSON.stringify(formik.values, null, 2)}</pre>
      <Container><h1 className="my-4 font-wright-bold-display-4">Car Quote Form</h1>
    <Form>
      <TextField label="Prefix" name="prefix" type="text"/>
      <TextField label="First Name" name="firstName" type="text"/>
      <TextField label="Last Name" name="lastName" type="text"/>
      <TextField label="Telephone Number" name="telephone" type="text"/>
      <TextField label="Address Line 1" name="addressLine1" type="text"/>
      <TextField label="Address Line 2" name="addressLine2" type="text"/>
      <TextField label="City" name="city" type="text"/>
      <TextField label="Postcode" name="postcode" type="text"/>
      <SelectVehicle label="Vehicle Type" name="vehicleType" type="select"/>
      <SelectEngineSize label="Engine Size" name="engineSize" type="select"/>
      <RadioAdditionalDrivers label="Do you wish to add additional Drivers?" name="additionalDrivers" type="radio"/>
      <br/>
      <RadioYesNo label="Will the vehicle be used for commercial purposes?" name="commercial" type="radio" />
      <RadioYesNo label="Will the vehicle be used outside the registered state?" name="registeredState" type="radio" />
      <RangeField label="What is the value of the vehicle?" name="currentValue" type="range" min="0" max ="50000" step="1000"/>
      <DatePickerField name="dateRegistered" label="What was the date of the vehicle's first registration?" type="date"/>
      <button className="btn btn-dark mt-3" type="submit">Submit</button>
    </Form> </Container>
      <QuoteModal show={modalShow} quote={returnedQuote} onHide={()=> setModalShow(false)}/>
    </div>
   
  )}

</Formik>
);

}
export default CarForm;

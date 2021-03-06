import React,{useState} from 'react';
import './Admin.css';
import 'bootstrap/dist/css/bootstrap.min.css';
import Accordion from 'react-bootstrap/Accordion';
import Form from 'react-bootstrap/Form';
import Button from 'react-bootstrap/Button';
import Container from 'react-bootstrap/Container';
import Row from 'react-bootstrap/Row';
import Col from 'react-bootstrap/Col';
import Table from 'react-bootstrap/Table';
import Alert from 'react-bootstrap/Alert'
import { Nav } from 'react-bootstrap';
import { Navbar } from 'react-bootstrap';
import logo from './logo.png';

import axios from 'axios';
import SERVER_URL from "../../utils/Constants";
import PhoneModal from "../PhoneModal/PhoneModal";

function Admin(){
  
  const [tableData,setTableData] = useState([]);
  const [ID,setID] = useState('');
  const [show, setShow] = useState(false);
  const [phoneNumber,setPhoneNumber] = useState('');
  const [quoteData,setQuoteData] = useState([]);
  const [modalShow, setModalShow] = useState(false);

  const quoteEndpoint = "http://localhost:8080/quotes";
  const prodEndpoint = `${SERVER_URL}/quotes`;

  function getUser(e){
    e.preventDefault();
    console.log(ID);

    axios.get(`${prodEndpoint}/${ID}`)
      .then((response)=>{
        console.log(response.data);
        setTableData(response.data);
      }).catch((e)=>{
        setTableData([]);
        alert(e.response.data.message);
      });
  }

  function getUserDelete(e){
    e.preventDefault();

    axios.get(`${prodEndpoint}/${ID}`)
      .then((response)=>{
        setShow(true);
        setTableData(response.data);
      })
      .catch((e)=>{alert(e.response.data.message)});

  }

  function AlertDismissibleExample() {
  
    if (show) {
      return (
        <Alert show={show} variant="danger">
        <Alert.Heading>Caution!</Alert.Heading>
        <p>
          Are you sure you want to delete the user?
        </p>
        <p>ID: {ID}</p>
        <p>Name: {tableData.prefix} {tableData.firstName} {tableData.lastName}</p>
        <hr />
        <div className="d-flex justify-content-end">
          <Button onClick={() => {setShow(false);
                                  deleteUser();}} 
                                  variant="outline-danger">
            Delete!
          </Button>
        </div>
      </Alert>
      );
    }
    return null;
  }

  function deleteUser(){
    if(tableData){

      axios.delete(`${prodEndpoint}/${ID}`)
      .then(()=>{window.alert("User Deleted!")})
      .catch((err)=>{console.log(err)});
    }
  }

  function putUserPhone(e){
    e.preventDefault();

    if(phoneNumber.length === 11){
      axios.put(`${prodEndpoint}/updatePhone`,{telephone:phoneNumber,id:ID})
        .then((response)=>{
          setQuoteData(response.data);
          setModalShow(true);
        })
        .catch((err)=>{console.log(err)});
    }else{
      window.alert("Invalid Phone Number!");
    }
  }


  return(<>
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
  <Accordion>
  <Accordion.Item eventKey="0">
    <Accordion.Header>Get User Data</Accordion.Header>
    <Accordion.Body>
    <Form>
  <Form.Group className="mb-3" controlId="formUserID">
    <Form.Label>User ID:</Form.Label>
    <Form.Control type="text" placeholder="ID" onChange={e=>setID(e.target.value)}/>
  </Form.Group>
  <Button variant="primary" type="submit" onClick={getUser}>
    Submit
  </Button>
</Form>
<Container>
  <Row>
    <Col id="userGetDisplay">
      <div>
        <Table>
          <thead>
            <tr>
              <th>ID</th>
              <th>First Name</th>
              <th>Last Name</th>
              <th>Vehicle Type</th>
              <th>Tel. Number</th>
              <th>Quote Amount</th>
            </tr>
          </thead>
          <tbody>
              <tr>
              <td>{tableData.id}</td>
              <td>{tableData.firstName}</td>
              <td>{tableData.lastName}</td>
              <td>{tableData.vehicleType}</td>
              <td>{tableData.telephone}</td>
              <td>$ {tableData.quoteAmount}</td>
            </tr>
          </tbody>
        </Table>
      </div>
    </Col>
  </Row>
</Container>
    </Accordion.Body>
  </Accordion.Item>
  <Accordion.Item eventKey="1">
    <Accordion.Header>Delete User</Accordion.Header>
    <Accordion.Body>
      <Form>
          <Form.Group className="mb-3" controlId="formUserID">
        <Form.Label>User ID:</Form.Label>
        <Form.Control type="text" placeholder="ID" onChange={e=>setID(e.target.value)}/>
      </Form.Group>
      <Button variant="primary" type="submit" onClick={getUserDelete}>
        Submit
      </Button>
      </Form>
      <AlertDismissibleExample />
    </Accordion.Body>
  </Accordion.Item>
  <Accordion.Item eventKey="3">
    <Accordion.Header>Update User</Accordion.Header>
    <Accordion.Body>
    <Form>
      <Form.Group className="mb-3" controlId="formUserID">
        <Form.Label>User ID:</Form.Label>
        <Form.Control type="text" placeholder="ID" onChange={e=>setID(e.target.value)}/>
      </Form.Group>
      <Form.Group className="mb-3" controlId="formUserID">
        <Form.Label>Phone Number: </Form.Label>
        <Form.Control type="number" placeholder="ID" onChange={e=>setPhoneNumber(e.target.value)}/>
      </Form.Group>
      <Button variant="primary" type="submit" onClick={putUserPhone}>
        Submit
      </Button>
      </Form>
    </Accordion.Body>
  </Accordion.Item>
</Accordion>
<PhoneModal quote={quoteData} show={modalShow} onHide={()=>{setModalShow(false)}}/>

  </>);
}

export default Admin;

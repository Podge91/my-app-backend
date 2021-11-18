import React from 'react';
import './RadioAdditionalDrivers.css';
import { useField,ErrorMessage,useFormikContext} from 'formik';
import { ToggleButton,ToggleButtonGroup} from 'react-bootstrap';

const RadioAdditionalDrivers = ({ label,...props}) => {

  const [field] = useField(props);
  const { handleChange } = useFormikContext();

  const radios = [{name:'0', value:'0'},
                  {name:'1', value:'1'},
                  {name:'2', value:'2'},
                  {name:'3', value:'3'},
                  {name:'4', value:'4'}
                ];


  return(<>
  <label htmlFor={field.name}>{label}</label>
  <br/>
  <ToggleButtonGroup name={field.name}>
    {radios.map((radio,idx)=>(
      <ToggleButton 
        key={idx}
        id={`radio-${idx}`}
        type='radio'
        variant='primary'
        name={field.name}
        value={radio.value}
        onChange={handleChange}
        checked={field.value === radio.value}
        >
          {radio.name}
        </ToggleButton>
    ))}
  </ToggleButtonGroup>

<ErrorMessage name={field.name}/>
<br/>
</>
);
};


export default RadioAdditionalDrivers;

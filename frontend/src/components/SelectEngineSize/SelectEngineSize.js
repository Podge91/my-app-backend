import React from 'react';
import './SelectEngineSize.css';
import { useField,ErrorMessage} from 'formik';

const SelectEngineSize = ({ label, ...props}) => {

  const [field, meta] = useField(props);


  return (<>
  <label htmlFor={field.name}>{label}</label>
  <br/>
  <select className={`form-select shadow-none ${meta.touched && meta.error && 'is-invalid'}`} type="range" min={field.min} max={field.max} step={field.step}
      {...field} {...props}
      autoComplete="off">
    <option value="">Select</option>
    <option value="1000">1000cc</option>
    <option value="1600">1600cc</option>
    <option value="2000">2000cc</option>
    <option value="2500">2500cc</option>
    <option value="3000">3000cc</option>
    <option value="other">Other</option>
  </select>
  <ErrorMessage name={field.name}/>
  <br/>
  </>
  )};


export default SelectEngineSize;

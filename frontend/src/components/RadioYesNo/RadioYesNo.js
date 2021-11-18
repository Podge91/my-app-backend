import { useField,ErrorMessage} from 'formik';
import React from 'react';
import './RadioYesNo.css';

const RadioYesNo = ({ label, ...props}) => {

  const [field, meta] = useField(props);


  return (<>
  <label htmlFor={field.name}>{label}</label>
  <br/>
  <select className={`form-select shadow-none ${meta.touched && meta.error && 'is-invalid'}`} type="range" min={field.min} max={field.max} step={field.step}
      {...field} {...props}
      autoComplete="off">
    <option value="">Select</option>
    <option value="yes">Yes</option>
    <option value="no">No</option>
  </select>
  <ErrorMessage name={field.name}/>
  <br/>
  </>
  )

}
export default RadioYesNo;
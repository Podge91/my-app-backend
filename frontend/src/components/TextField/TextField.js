import { useField,ErrorMessage } from 'formik';
import React from 'react';
import './TextField.css';

const TextField = ({ label, ...props}) => {
  const [field, meta] = useField(props);

  return (
    <div className="mb-2">
      <label htmlFor={field.name}>{label}</label>
      <input className={`form-control shadow-none ${meta.touched && meta.error && 'is-invalid'}`} type="text" 
      {...field} {...props}
      autoComplete="off"/>
      <ErrorMessage name={field.name}/>
    </div>
  )

}
export default TextField;
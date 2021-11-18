import { useField,ErrorMessage } from 'formik';
import React from 'react';
import { Badge } from 'react-bootstrap';
import './RangeField.css';

const RangeField = ({ label, ...props}) => {
  const [field, meta] = useField(props);

  return (
    <div className="mb-2">
      <label htmlFor={field.name}>{label}</label>
      <h1><Badge bg="primary">${field.value}</Badge></h1>
      <input className={`form-range shadow-none ${meta.touched && meta.error && 'is-invalid'}`} type="range" min={field.min} max={field.max} step={field.step}
      {...field} {...props}
      autoComplete="off"
      />
      <ErrorMessage name={field.name}/>
    </div>
  )

}
export default RangeField;
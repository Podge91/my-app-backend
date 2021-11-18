import { useField,ErrorMessage} from 'formik';
import React from 'react';
import './SelectVehicle.css';

const SelectVehicle = ({ label, ...props}) => {

  const [field, meta] = useField(props);


  return (<>
  <label htmlFor={field.name}>{label}</label>
  <br/>
  <select className={`form-select shadow-none ${meta.touched && meta.error && 'is-invalid'}`} type="range" min={field.min} max={field.max} step={field.step}
      {...field} {...props}
      autoComplete="off">
    <option value="">Select</option>
    <option value="cabriolet">Cabriolet</option>
    <option value="coupe">Coupe</option>
    <option value="estate">Estate</option>
    <option value="hatchback">Hatchback</option>
    <option value="other">Other</option>
  </select>
  <ErrorMessage name={field.name}/>
  <br/>
  </>
  )

}
export default SelectVehicle;
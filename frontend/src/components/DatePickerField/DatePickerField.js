import React from "react";
import { ErrorMessage, useField, useFormikContext} from "formik";
import { Form } from "react-bootstrap";

export const DatePickerField = ({ label, ...props }) => {
  
  const { handleChange,handleBlur } = useFormikContext();
  const [field,meta] = useField(props);
  
  return (<>
    <label htmlFor={field.name}>{label}</label>
    <br />

      <Form.Control
        type="date"
        name="dateRegistered"
        onChange={handleChange}
        onBlur={handleBlur}
        value={field.value}
        className={`form-date shadow-none ${meta.touched && meta.error && 'is-invalid'}`}
        />

    <br />
    <ErrorMessage name={field.name} />
    </>
  );
};
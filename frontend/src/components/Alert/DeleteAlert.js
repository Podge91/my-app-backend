import React,{ useState } from 'react';
import './Alert.css';
import Alert from 'react-bootstrap/Alert';
import Button from 'react-bootstrap/Button';


function DeleteAlert() {
  const [show, setShow] = useState(true);

  return (
    <>
      <Alert show={show} variant="danger">
        <Alert.Heading>Caution!</Alert.Heading>
        <p>
          Are you sure you want to delete the user?
        </p>
        <hr />
        <div className="d-flex justify-content-end">
          <Button onClick={() => setShow(false)} variant="outline-danger">
            Close me y'all!
          </Button>
        </div>
      </Alert>

      {!show && <Button onClick={() => setShow(true)}>Show Alert</Button>}
    </>
  );
}

export default DeleteAlert;

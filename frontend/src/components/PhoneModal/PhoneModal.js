import React from 'react';
import './PhoneModal.css';
import {Button, Modal} from "react-bootstrap";

const PhoneModal = (props) => {

    let quote = props.quote;

 return(
    <>
        <Modal {...props} size="lg" aria-labelledby="contained-modal-title-vcenter" centered>
            <Modal.Header>
                <Modal.Title id="contained-modal-title-vcenter">
                    Updated Phone Number!
                </Modal.Title>
            </Modal.Header>
            <Modal.Body>
                <h4>Phone number updated successfully!</h4>
                <h4>Name</h4>
                <p>{quote.prefix} {quote.firstName} {quote.lastName}</p>
                <br/>
                <h4>New Phone Number</h4>
                <p>{quote.telephone}</p>
            </Modal.Body>
            <Modal.Footer>
                <Button variant="primary" onClick={props.onHide}>
                    Close
                </Button>
            </Modal.Footer>
        </Modal>
    </>
 );
};


export default PhoneModal;

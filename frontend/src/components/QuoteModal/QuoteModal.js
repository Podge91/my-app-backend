import React from 'react';
import './QuoteModal.css';
import {Button, Modal} from "react-bootstrap";

const QuoteModal = (props) => {

    let quote = props.quote;

if (props.show) {

    return (
        <>
            <Modal {...props} size="lg" aria-labelledby="contained-modal-title-vcenter" centered>
                <Modal.Header closeButton>
                    <Modal.Title id="contained-modal-title-vcenter">
                        Your Quote
                    </Modal.Title>
                </Modal.Header>
                <Modal.Body>
                    <h4>Name</h4>
                    <p>`${quote.prefix} ${quote.firstName} ${quote.lastName}`</p>
                    <br/>
                    <h4>Address</h4>
                    <p>`${quote.addressLine1}`</p>
                    <p>`${quote.addressLine2}`</p>
                    <p>`${quote.city}`</p>
                    <br/>
                    <h4>Vehicle Details</h4>
                    <p>{quote.vehicleType}</p>
                    <p>{quote.engineSize}</p>
                    <p>{quote.currentValue}</p>
                    <p>{quote.dateRegistered}</p>
                    <p>{quote.additionalDrivers}</p>
                    <p>{quote.commercial}</p>
                    <p>{quote.registeredState}</p>
                    <br/>
                    <h4>Quote Amount</h4>
                    <p>{quote.quoteAmount}</p>
                </Modal.Body>
                <Modal.Footer>
                    <Button variant="primary" onClick={props.onHide}>
                        Close
                    </Button>
                </Modal.Footer>
            </Modal>
        </>
    );
}else {return (null);}

};


export default QuoteModal;

import React from 'react';
import './QuoteModal.css';
import {Button, Modal} from "react-bootstrap";

const QuoteModal = (props) => {

    let quote = props.quote;
    let responseCommercial;
    let responseRegisteredState;

if (props.show) {

    if (quote.commercial){
        responseCommercial = "Yes";
    } else if (!quote.commercial){
        responseCommercial = "No";
    }

    if (quote.registeredState){
        responseRegisteredState = "Yes";
    } else if (!quote.registeredState){
        responseRegisteredState = "No";
    }

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
                    <p>{quote.prefix} {quote.firstName} {quote.lastName}</p>
                    <br/>
                    <h4>Address</h4>
                    <p>{quote.addressLine1}</p>
                    <p>{quote.addressLine2}</p>
                    <p>{quote.city}</p>
                    <br/>
                    <h4>Vehicle Details</h4>
                    <p>Vehicle Type: {quote.vehicleType.charAt(0).toUpperCase() + quote.vehicleType.slice(1)}</p>
                    <p>Engine Size: {quote.engineSize}cc</p>
                    <p>Current Value: ${quote.currentValue}</p>
                    <p>Date Registered: {quote.dateRegistered}</p>
                    <p>Additional Drivers: {quote.additionalDrivers}</p>
                    <p>Will the vehicle be used for commercial purposes? {responseCommercial}</p>
                    <p>Will the vehicle be used outside the registered state? {responseRegisteredState}</p>
                    <br/>
                    <h4>Quote Amount</h4>
                    <p>${(Math.round(quote.quoteAmount*100)/100).toFixed(2)}</p>
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

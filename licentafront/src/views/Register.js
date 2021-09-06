import React from "react";
import "../assets/scss/views/Register.scss";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import Row from "react-bootstrap/Row";
import Col from "react-bootstrap/Col";
import "react-datepicker/dist/react-datepicker.css";
import DatePicker from "react-datepicker";
import {Link} from "react-router-dom";

export default function Register() {
  return (
      <div className="register-container">
        <h1>Lifey</h1>
        <Form>
          <Row className="mb-3">
            <Form.Group md="4" controlId="validationCustom01">
              <Form.Label>First name</Form.Label>
              <Form.Control
                  required
                  type="text"
                  placeholder="First name"
                  defaultValue="Florin"
              />
              <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            </Form.Group>
            <Form.Group md="4" controlId="validationCustom02">
              <Form.Label>Last name</Form.Label>
              <Form.Control
                  required
                  type="text"
                  placeholder="Last name"
                  defaultValue="Popescu"
              />
              <Form.Control.Feedback>Looks good!</Form.Control.Feedback>
            </Form.Group>
            <Form.Group className="mb-3" controlId="formBasicEmail">
              <Form.Label>Email address</Form.Label>
              <Form.Control
                  required
                  type="email"
                  placeholder="Enter email"
                  defaultValue="florin.popescu@gmail.com"
              />
            </Form.Group>

            <fieldset>
              <Form.Group as={Row} className="mb-3">
                <Form.Label as="legend" column sm={2}>
                  Gender
                </Form.Label>
                <Col sm={10}>
                  <Form.Check
                      type="radio"
                      label="Male"
                      name="formHorizontalRadios"
                      id="formHorizontalRadios2"
                  />
                  <Form.Check
                      type="radio"
                      label="Female"
                      name="formHorizontalRadios"
                      id="formHorizontalRadios3"
                  />
                </Col>
              </Form.Group>
            </fieldset>

            <DatePicker className="form-datepicker"/>

            <Form.Control as="select">
              <option>Open this select menu</option>
              <option value="1">One</option>
              <option value="2">Two</option>
              <option value="3">Three</option>
            </Form.Control>
          </Row>
          <Link to="/">
            <Button type="submit">Register</Button>
          </Link>
        </Form>
      </div>
  );
}

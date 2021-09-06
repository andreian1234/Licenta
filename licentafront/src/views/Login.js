import React from "react";
import "../assets/scss/views/Login.scss";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import {Link} from "react-router-dom";

export default function Login() {
  return (
      <div className="login-container">
        <h1>Lifey</h1>
        <Form>
          <Form.Group className="mb-3" controlId="formBasicEmail">
            <Form.Label>Email address</Form.Label>
            <Form.Control type="email" placeholder="Enter email"/>
          </Form.Group>

          <Form.Group className="mb-3" controlId="formBasicPassword">
            <Form.Label>Password</Form.Label>
            <Form.Control type="password" placeholder="Password"/>
          </Form.Group>
          <Link to="/home">
            <Button variant="primary" type="submit" className="login-btn">
              Submit
            </Button>
          </Link>
          <Link to="/register" className="link">
            <p>Don't have an account? Register here!</p>
          </Link>
        </Form>
      </div>
  );
}

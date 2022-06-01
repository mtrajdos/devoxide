import React, { useState } from "react";
import { Form, Button } from "react-bootstrap";
import { connect } from "react-redux";
import { loginRequested } from "../thunks";
import { useHistory } from "react-router-dom";

const Login = ({ onLoginPressed }) => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");

  const history = useHistory();

  const body = {
    email,
    password,
  };

  return (
    <div className="components">
      <Form>
        <h3>Login</h3>
        <Form.Group controlId="email">
          <Form.Text className="requested-email-text"></Form.Text>
          <Form.Label>Email:</Form.Label>
          <Form.Control
            type="text"
            name="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            placeholder="type email here"
          />
        </Form.Group>
        <Form.Group controlId="password">
          <Form.Text className="requested-password-text"></Form.Text>
          <Form.Label>Password:</Form.Label>
          <Form.Control
            type="password"
            name="password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            placeholder="type password here"
          />
        </Form.Group>
        <Button
          variant="success"
          onClick={() => {
            console.log(body);
            onLoginPressed(body);
            setEmail("");
            setPassword("");
            history.push("/");
          }}
        >
          Login
        </Button>
      </Form>
    </div>
  );
};

const mapStateToProps = (state) => ({});

const mapDispatchToProps = (dispatch) => ({
  onLoginPressed: (body) => dispatch(loginRequested(body)),
});

export default connect(mapStateToProps, mapDispatchToProps)(Login);

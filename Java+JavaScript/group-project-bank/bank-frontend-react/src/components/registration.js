import React, { useState, useEffect } from "react";
import { connect } from 'react-redux';
import { getAllUsers } from '../selectors';
import { createUserRequest, loadAll } from '../thunks';
import { Form, Button } from "react-bootstrap";
import { useHistory } from "react-router-dom";


const NewUserForm = ({ emailMessage, onCreatePressed, startLoadingAll }) => {
    const [ firstName, setfirstName ] = useState('');
    const [ lastName, setlastName ] = useState('');
    const [ email, setemail ] = useState('');
    const [ password, setpassword ] = useState('');

    const history = useHistory();

    useEffect(() => {
        startLoadingAll();
    }, [] );

    const body = {
        firstName,
        lastName,
        email,
        password,
    }

    return (

        <div className="components">
            {emailMessage === true && 
                    <p>That email address is in use, please try another</p>}
            <Form>
                <Form.Group controlId="first-name">
                    <Form.Text className="requested-loan-text">
                        <h3>Create your profile</h3>
                    </Form.Text>
                    <Form.Label>First Name:</Form.Label>
                    <Form.Control type="text" name="firstName" value={firstName} onChange={e => setfirstName(e.target.value)} placeholder="type first name here"/>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Last Name:</Form.Label>
                    <Form.Control type="text" name="lastName" value={lastName} onChange={e => setlastName(e.target.value)} placeholder="type last name here"/>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Email:</Form.Label>
                    <Form.Control type="text" name="email" value={email} onChange={e => setemail(e.target.value)} placeholder="type email here"/>
                </Form.Group>
                <Form.Group>
                    <Form.Label>Password</Form.Label>
                    <Form.Control type="password" name="password" value={password} onChange={e => setpassword(e.target.value)} placeholder="type password here"/>
                </Form.Group>
                <Button variant="success" 
                    onClick={() => {
                        onCreatePressed(body);
                        setfirstName("");
                        setlastName("");
                        setemail("");
                        setpassword("");
                        history.push("/")
                    }}>
                    Create New User
                </Button>
            </Form>
        </div>
    );
};

const mapStateToProps = state => ({
    users: getAllUsers(state),
});

const mapDispatchToProps = dispatch => ({
    startLoadingAll: () => dispatch(loadAll()),
    onCreatePressed: body => dispatch(createUserRequest(body)),
});

export default connect(mapStateToProps, mapDispatchToProps)(NewUserForm);
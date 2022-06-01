import { axiosMock } from 'axios-mock-adapter';
import { expect } from 'chai';
import sinon from 'sinon';
import { createUserRequest } from '../thunks';

// describe('The CreateNewUser thunk', () => {
//     it('Dispatches the correct actions in the success scenario', () => {
//         const dispatch = sinon.spy();

//         const newUser = [{firstName: 'Mark', lastName: 'Smith', email: 'mark@email.com', password: 'password'}];
//         axiosMock.onPost('http://localhost:8088/login/RegisterUser', newUser);

//         const expectedAction = { type: 'CREATE_USER', payload: { users: newUser } };

//         createUserRequest(newUser);

//         expect(dispatch.getCall(0).args[0]).to.deep.equal(expectedAction);
        
//         axiosMock.reset();

//     })
// })
import { expect } from 'chai';
import { users } from '../reducers';

// describe('The user reducer', () => {
//     it('Adds a new user when CREATE_USER action is received', () => {
//         const User = { firstName: 'mark', lastName: 'smith', email: 'mark@gmail.com', password: 'password'};

//         const Action = {
//             type: 'CREATE_USER',
//             payload: {
//                 user: User,
//             },
//         };

//         const originalState = { data: [] };

//         const expected = {
//             data: [User],
//         };

//         const actual = users(originalState, Action);

//         expect(actual).to.deep.equal(expected);
//     })
// })

describe('The user reducer', () => {
    it('Retrieves the user from database and adds to front', () => {
        const user = {firstName: 'mark', lastName: 'smith', email: 'mark@gmail.com', password: 'password'};

        const Action = {
            type: 'LOGIN_USER',
            payload: {
                user: user,
            },
        };

        const originalState = { data: [], isLoggedIn: false, loggedInUser: {} };

        const expected = {
            data: [],
            isLoggedIn: true,
            loggedInUser: user,
        };

        const actual = users(originalState, Action);

        expect(actual).to.deep.equal(expected);
    })
})
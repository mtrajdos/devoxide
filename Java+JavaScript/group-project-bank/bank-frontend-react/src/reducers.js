import { CREATE_USER, LOGIN_USER, LOAD_ALL_USERS, LOGOUT_USER } from './actions';

const initialState = { 
    noUserFoundMessage: "",
    passwordMessage: "",
    emailMessage: false,
    isLoggedIn: false,
    loggedInUser: {},
    data: [],
    userAccounts: []
};

export const users = ( state = initialState, action ) => {
    const { type, payload } = action;

    switch(type) {
        case LOAD_ALL_USERS: {
            const { allUsers } = payload;
            console.log("Retreiving All Users")
            return {
                ...state,
                data: allUsers,
            }
        }
        case CREATE_USER: {
            const { user } = payload;
            console.log(user);
            if (user.data === "") {
                console.log("Email already in use");
                return {
                    ...state,
                    emailMessage: true
                }
            } else {
                console.log("New user created")
                return {
                    ...state,
                    data: state.data.data.concat(user.data),
                };
            }
            
        }
        case LOGIN_USER: {
            const  { user }  = payload;
            console.log("User loggin in")
            return {
                ...state,
                isLoggedIn: true,
                loggedInUser: user
            }
        }
        case LOGOUT_USER: {
            console.log("User logging out")
            return {
                ...state,
                isLoggedIn: false,
                loggedInUser: {},
                userAccounts:[]
            }
        }
        default:
            return state;
    }
}
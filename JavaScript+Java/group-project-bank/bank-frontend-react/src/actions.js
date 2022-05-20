export const LOAD_ALL_USERS = 'LOAD_ALL_USERS';
export const loadAllUsers = allUsers => ({
    type: LOAD_ALL_USERS,
    payload: { allUsers },
});

export const CREATE_USER = 'CREATE_USER';
export const createUser = user => ({
    type: CREATE_USER,
    payload: { user },
});

export const LOGIN_USER = 'LOGIN_USER';
export const loginUser = user => ({
    type: LOGIN_USER,
    payload: { user },
})

export const LOGOUT_USER = 'LOGOUT_USER';
export const logoutUser = () => ({
    type: LOGOUT_USER,
})

export const NEW_LOAN = 'NEW_LOAN';
export const newLoan = loan => ({
    type: NEW_LOAN,
    payload: { loan }
})

export const RETURN_MORTGAGE = 'RETURN_MORTGAGE';
export const returnCheckedMortgage = checkedMortgage => ({
    type: RETURN_MORTGAGE,
    payload: { checkedMortgage }
})
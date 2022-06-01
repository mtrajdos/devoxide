import { createSelector } from 'reselect';

export const getLoggedInBool = state => state.users.isLoggedIn;

export const getAllUsers = state => state.users.data;

export const getLoggedInUser = state => state.users.loggedInUser.data;

export const getMortgage = state => state.loans.mortgage.data;

export const getLoanDetails = state => state.loans.loan.data;

export const getAll = createSelector(
    getAllUsers,
)
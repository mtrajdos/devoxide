import React from 'react';
import { render, unmountComponentAtNode } from "react-dom";
import {
    BrowserRouter as Router,
    Route,
  } from 'react-router-dom';
import { act } from "react-dom/test-utils";
import Enzyme, { shallow } from 'enzyme';
import 'jest-enzyme';
import NavBar, { logout }from './NavBar';

let container = null;
const mockUser = {
    firstName: "Doug",
    lastName: "Judy",
    username: "DJudy",
    address: "Brooklyn"
};
beforeEach(() => {
  // setup a DOM element as a render target
  container = document.createElement("div");
  document.body.appendChild(container);
  localStorage.setItem('firstName', mockUser.firstName);
                localStorage.setItem('lastName', mockUser.lastName);
                localStorage.setItem('username', mockUser.username);
                localStorage.setItem('address', mockUser.address);
                localStorage.setItem('loggedIn', true);
});

afterEach(() => {
  // cleanup on exiting
  unmountComponentAtNode(container);
  container.remove();
  container = null;
});


it('that logout() clears local storage', () => {
  const onClick = jest.fn();
  act(() => {
    render(<Router><NavBar onClick={onClick}/></Router>, container);
  });

  //get ahold of button element
  const logoutButton = document.querySelector("[data-testid=logout]")
  expect(logoutButton.innerHTML).toBe("<a href=\"/\">Logout</a>");

  act(() => {
    logoutButton.dispatchEvent(new MouseEvent("click", { bubbles: true }));
  });

  expect(onClick).toHaveBeenCalledTimes(1);
  expect(logoutButton.innerHTML).toBe("<a href=\"/\">Login</a>");

});

it('that Hotels component renders', () => {
  const wrapper = shallow(<Router><NavBar /></Router>);
  expect(wrapper.exists()).toBe(true);
});

it("renders with text content", () => {
    act(() => {
      render(<Router><NavBar /></Router>, container);
    });
    expect(container.textContent).toBe("Home Logout Account Hotels");
  });
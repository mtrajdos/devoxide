import React from 'react';
import Enzyme, { shallow } from 'enzyme';
import Account from './Account';
import Adapter from 'enzyme-adapter-react-16';
import { render, unmountComponentAtNode } from "react-dom";
import { act } from "react-dom/test-utils";


Enzyme.configure({ adapter: new Adapter() });

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

  it("renders with a mock User", () => {
    act(() => {
      render(<Account user={mockUser}/>, container);
    });
    expect(container.textContent).toBe("AccountYour user details are:First name: Doug Last name: Judy Username: DJudy Address: Brooklyn ");
  });


test('That there is a h3 on Account component', () => {
  const h3 = shallow(<Account />);
  expect(h3.exists()).toBe(true);
});

test('App component renders', () => {
  const wrapper = shallow(<Account />);
  expect(wrapper.exists()).toBe(true);
});


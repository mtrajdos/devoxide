import React from 'react';
import Enzyme, { shallow } from 'enzyme';
import Landing from './Landing';
import { render, unmountComponentAtNode } from "react-dom";
import { act } from "react-dom/test-utils";

let container = null;
beforeEach(() => {
    container = document.createElement("div");
    document.body.appendChild(container);
});
  
afterEach(() => {
    unmountComponentAtNode(container);
    container.remove();
    container = null;
});

it("renders with the text 'Landing Page' ", () => {
    act(() => {
        render(<Landing />, container);
    });
    expect(container.textContent).toBe("Landing Page");
});

test('that Hotels component renders', () => {
    const wrapper = shallow(<Landing />);
    expect(wrapper.exists()).toBe(true);
});
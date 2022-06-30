import React from 'react';
import Enzyme, { shallow } from 'enzyme';
import Error from './Error';
import { render, unmountComponentAtNode } from "react-dom";
import { act } from "react-dom/test-utils";

let container = null;
beforeEach(() => {
    // setup a DOM element as a render target
    container = document.createElement("div");
    document.body.appendChild(container);
});
  
afterEach(() => {
 // cleanup on exiting
    unmountComponentAtNode(container);
    container.remove();
    container = null;
});

it("renders with the text 'There are no hotels, check your sources!!!' ", () => {
    act(() => {
      render(<Error />, container);
    });
    expect(container.textContent).toBe("There are no hotels, check your sources!!!");
});

test('that Error component renders', () => {
    const wrapper = shallow(<Error />);
    expect(wrapper.exists()).toBe(true);
});
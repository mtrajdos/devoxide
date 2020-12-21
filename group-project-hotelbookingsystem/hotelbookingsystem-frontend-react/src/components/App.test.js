import React from 'react';
import Enzyme, { shallow } from 'enzyme';
import { render } from '@testing-library/react';
import App from './App';
import Adapter from 'enzyme-adapter-react-16';

Enzyme.configure({ adapter: new Adapter() });

test('renders Hotel Booking System in header', () => {
  const { getByText } = render(<App />);
  const linkElement = getByText(/Hotel Booking System/i);
  expect(linkElement).toBeInTheDocument();
});

test('That there is a h1 on app component', () => {
  const h1 = shallow(<App />);
  expect(h1.exists()).toBe(true);
});

test('App component renders', () => {
  const wrapper = shallow(<App />);
  expect(wrapper.exists()).toBe(true);
});


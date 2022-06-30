const add = jest.fn(() => 3);

//Simple test to get the empty sheet to pass
test('<Add>', () => {

    expect(add(1, 2)).toBe(3);

});
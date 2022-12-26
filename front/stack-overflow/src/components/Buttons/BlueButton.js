import styled from "styled-components";

const Container = styled.span`
  display: inline-block;
  font-family: inherit;
  color: white;
  background-color: hsl(206, 100%, 52%);
  border-radius: 3px;
  box-shadow: rgba(255, 255, 255, 0.4);
  padding: 10.4px;
  border: 1px solid transparent;

  &:hover {
    background-color: hsl(206, 100%, 40%);
    cursor: pointer;
  }

  & > span {
    font-size: 13px;
    color: white;
    text-align: center;
  }
`;

const BlueButton = ({ text }) => {
  return <Container> {text} </Container>;
};

export default BlueButton;

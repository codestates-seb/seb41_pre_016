import styled, { css } from "styled-components";

const Container = styled.div`
  background-color: white;
  border: 1px solid var(--bc-medium);
  border-radius: 3px;
  box-shadow: var(--bs-sm);
  position: relative;
  margin-bottom: 16px;
  font-size: 13px;
`;

const Header = styled.h2`
  display: block;
  color: var(--black-600);
  background-color: var(--black-025);
  font-size: 15px;
  line-height: 16px;
  padding: 12px 15px;
  border-bottom: 1px solid var(--black-075);
  font-weight: ${(props) => (props.bold ? props.bold : 400)};
`;

const Content = styled.div`
  margin: 0;
  color: var(--black-600);
  padding: 16px 16px;
`;

const Silver = ({ header, contents, bold }) => {
  return (
    <Container>
      <Header bold={bold}>{header}</Header>
      <Content>{contents}</Content>
    </Container>
  );
};

export default Silver;

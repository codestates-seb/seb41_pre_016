import styled from "styled-components";

const Container = styled.div`
  width: auto;
  padding-top: 10px;
  padding-bottom: 32px;
`;

const Header = styled.div`
  display: flex;
  align-items: center;
`;

const AnswerCount = styled.h2`
  font-size: 19px;
`;

const Answer = ({ count }) => {
  return (
    <Container>
      <Header>
        <AnswerCount>{count} Answer</AnswerCount>
      </Header>
    </Container>
  );
};

export default Answer;

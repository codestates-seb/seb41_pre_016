import styled, { css } from "styled-components";
import useStore from "../../store/SummaryStates";

const Container = styled.div`
  display: flex;
  justify-content: flex-end;
  cursor: pointer;

  button {
    &:nth-child(1) {
      border-top-left-radius: 3px;
      border-bottom-left-radius: 3px;
    }
    &:nth-child(2) {
      border-top-right-radius: 3px;
      border-bottom-right-radius: 3px;
    }
  }
`;

const Tabbox = styled.button`
  font-size: 13px;
  padding: 9.6px;
  color: var(--black-500);
  border: 1px solid var(--black-300);
  background-color: white;
  cursor: pointer;

  &:hover {
    background-color: var(--black-025);
  }

  ${(props) => {
    props.selected &&
      css`
        color: var(--black-700);
        border: 1px solid var(--black-400);
        background-color: var(--black-075);
      `;
  }}
`;

const TabButton = () => {
  const { getQuestions } = useStore();
  return (
    <Container>
      <Tabbox onClick={() => getQuestions("date", 1, 10)}>Newest</Tabbox>
      <Tabbox onClick={() => getQuestions("no-answer", 1, 10)}>
        Unanswered
      </Tabbox>
    </Container>
  );
};

export default TabButton;

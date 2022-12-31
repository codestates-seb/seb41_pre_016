import styled from "styled-components";
import useStore from "../../store/SummaryStates";

const Container = styled.button`
  display: flex;
  align-items: center;
  font-size: 13px;
  line-height: 25px;
  padding: 0 8px;
  border-radius: 3px;
  color: var(--fc-medium);
  border: 1px solid var(--bc-medium);
  background-color: transparent;
  cursor: pointer;
`;

const Selected = styled(Container)`
  color: white;
  border: 1px solid transparent;
  background-color: rgb(244, 130, 37);
`;

const PageButton = ({ number, selected }) => {
  const { sortType, pageInfo, getQuestions } = useStore();

  if (number === "Next") {
    // totalPages 와 pageInfo.page 가 같으면 렌더링 안함
    if (pageInfo.totalPages !== pageInfo.page) {
      return (
        <Container onClick={() => getQuestions(sortType, pageInfo.page + 1)}>
          {number}
        </Container>
      );
    }
  } else if (number === "Prev") {
    // 1번 페이지인경우 Prev 버튼 렌더링하지 않음
    if (pageInfo.page !== 1) {
      return (
        <Container onClick={() => getQuestions(sortType, pageInfo.page - 1)}>
          {number}
        </Container>
      );
    }
  } else if (selected) {
    return <Selected>{number}</Selected>;
  } else {
    return (
      <Container onClick={() => getQuestions(sortType, number)}>
        {number}
      </Container>
    );
  }
};

export default PageButton;

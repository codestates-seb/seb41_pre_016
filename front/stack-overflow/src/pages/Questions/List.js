import Summary from "./Contents/Summary";
import useStore from "./Store/SummaryStates";
import TabButton from "./Buttons/TabButton";
import { useEffect } from "react";
import styled from "styled-components";
import Pagination from "./Contents/Pagination";

const Container = styled.div`
  display: flex;
  justify-content: space-between;
  padding-top: 12px;
  padding-bottom: 12px;
  align-items: center;
  border-bottom: 1px solid var(--black-075);
`;

const QuestionNumber = styled.div`
  display: inline-block;
  font-size: 17px;
`;

const List = () => {
  const { questions } = useStore();

  return (
    <>
      <Container>
        <QuestionNumber>23,343,145 questions</QuestionNumber>
        <TabButton />
      </Container>
      {questions
        ? questions.map((el) => {
            return (
              <Summary
                key={el.id}
                title={el.title}
                content={el.content}
                tags={el.tags}
                vote={el.votes}
                answer={el.answerCount}
                views={el.views}
                bounty={50}
              />
            );
          })
        : null}
      <Pagination />
    </>
  );
};

export default List;

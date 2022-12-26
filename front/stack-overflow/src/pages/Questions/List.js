import Summary from "../../components/Contents/Summary";
import useStore from "../../store/SummaryStates";
import TabButton from "../../components/Buttons/TabButton";
import { useState, useEffect, CSSProperties } from "react";
import styled from "styled-components";
import Pagination from "../../components/Contents/Pagination";
import { PacmanLoader } from "react-spinners";

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

const SpinnerContainer = styled.div`
  width: 100%;
  height: 50vh;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const List = () => {
  const { questions, pageInfo } = useStore();

  return (
    <>
      {pageInfo ? (
        <Container>
          <QuestionNumber>{pageInfo.totalElements} questions</QuestionNumber>
          <TabButton />
        </Container>
      ) : null}
      {questions ? (
        questions.map((el, idx) => {
          return (
            <Summary
              key={idx}
              title={el.title}
              content={el.content}
              tags={el.tags}
              vote={el.votes}
              answer={el.answerCount}
              views={el.views}
              name={el.name}
              userId={el.userId}
            />
          );
        })
      ) : (
        <SpinnerContainer>
          <PacmanLoader color="var(--black-200)" />
        </SpinnerContainer>
      )}
      <Pagination />
    </>
  );
};

export default List;

import Summary from "../../components/Contents/Summary";
import useStore from "../../store/SummaryStates";
import TabButton from "../../components/Buttons/TabButton";
import { useState, useEffect, CSSProperties } from "react";
import styled from "styled-components";
import Pagination from "../../components/Contents/Pagination";
import Loading from "../../components/Widget/Loading";

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
  const { questions, isLoading, pageInfo } = useStore();

  return (
    <>
      {pageInfo ? (
        <Container>
          <QuestionNumber>{pageInfo.totalElements} questions</QuestionNumber>
          <TabButton />
        </Container>
      ) : null}
      {!isLoading ? (
        questions?.map((el, idx) => {
          return (
            <Summary
              key={idx}
              id={el.questionId}
              title={el.title}
              content={el.content}
              tags={el.tags}
              vote={el.votes}
              answer={el.answer_count}
              views={el.views}
              name={el.name}
              userId={el.userId}
              modTime={el.createdAt}
            />
          );
        })
      ) : (
        <Loading />
      )}
      {pageInfo ? <Pagination pageInfo={pageInfo} /> : null}
    </>
  );
};

export default List;

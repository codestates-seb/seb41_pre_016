import styled from "styled-components";
import { useEffect, useState } from "react";
import useStore from "../../store/ArticleStates";
import Header from "./Header";
import Aside from "./Aside";
import Content from "./Content";
import Sidebar from "../Questions/Sidebar";
import AnswerCount from "./AnswerCount";
import { useParams } from "react-router-dom";

const Container = styled.div`
  width: 100%;
  max-width: 1100px;
  height: 100vh;
  background-color: white;
  padding: 24px;
  border-left: 1px solid hsl(210, 8%, 85%);
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI Adjusted",
    "Segoe UI", "Liberation Sans", sans-serif;
`;

const TextContainer = styled.div`
  display: grid;
  float: left;
  width: calc(100% - 300px - 24px);
  grid-template-columns: max-content 1fr;
`;

const SidebarContainer = styled.div`
  margin-left: 24px;
  float: left;
  width: 300px;
`;

const Article = () => {
  const question = useStore();
  const { questionId } = useParams();

  useEffect(() => {
    question.getArticle(questionId);
    window.scrollTo(0, 0);
  }, []);

  return (
    <Container>
      <Header />
      <TextContainer>
        <Aside votes={question.votes} />
        <Content
          name={question.name}
          content={question.content}
          tags={question.tags}
          id={question.useId}
          author="true"
        />
        {question?.answers?.length !== 0 ? (
          <>
            <AnswerCount count={question.answer_count} />
            {question?.answers?.map((el) => {
              return (
                <>
                  <Aside votes={el.votes} />
                  <Content
                    content={el.content}
                    name={question.name}
                    id={question.useId}
                  />
                </>
              );
            })}
          </>
        ) : null}
      </TextContainer>
      <Sidebar />
    </Container>
  );
};

export default Article;

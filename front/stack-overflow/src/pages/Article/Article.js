import styled from "styled-components";
import { useEffect, useRef } from "react";
import useStore from "../../store/ArticleStates";
import Header from "./Header";
import Aside from "./Aside";
import Content from "./Content";
import Sidebar from "../Questions/Sidebar";
import AnswerCount from "./AnswerCount";
import { useParams } from "react-router-dom";
import { Editor } from "@toast-ui/react-editor";
import ContentMessageTextarea from "../../components/AskQuestion/UtilComponents/ContentMessageTextarea";
import PostButton from "../../components/AskQuestion/UtilComponents/PostButton";
import { useCookies } from "react-cookie";
import { QuestionStore } from "../../store/zustandQuestion";
import { userInfoStore } from "../../store/zustandUserInfo";

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

const BarContainer = styled.div`
  display: flex;
`;

const MainBar = styled.div`
  width: calc(100% - 300px - 24px);
  display: flex;
  flex-direction: column;
  flex-wrap: nowrap;
  background-color: white;
  font-family: -apple-system, BlinkMacSystemFont, "Segoe UI Adjusted",
    "Segoe UI", "Liberation Sans", sans-serif;
`;

const TextContainer = styled.div`
  display: grid;
  float: left;
  width: 100%;
  grid-template-columns: max-content 1fr;
`;

const SidebarContainer = styled.div`
  margin-left: 24px;
  float: left;
  width: 300px;
`;

const PostBtnContainer = styled.div`
  margin-top: 50px;
  display: inline-block;
`;

const Article = () => {
  const question = useStore();
  const { questionId } = useParams();
  const commentRef = useRef();
  const [cookies] = useCookies(["access_jwt"]);
  const userInfo = userInfoStore((state) => state.userInfo);
  const { postQuestion } = QuestionStore();

  useEffect(() => {
    question.getArticle(questionId);
    window.scrollTo(0, 0);
  }, []);

  const handlePostAnswer = async () => {
    const contents = commentRef.current?.getInstance().getHTML();
    const reqObject = {
      questionId: questionId,
      content: contents,
    };
    const cookieObject = {
      Authorization: cookies.access_jwt.Authorization,
      Refresh: cookies.access_jwt.Refresh,
    };
    await postQuestion("/answer", userInfo.userId, reqObject, cookieObject);
  };

  return (
    <Container>
      <Header />
      <BarContainer>
        <MainBar>
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
                        name={el.name}
                        id={el.userId}
                      />
                    </>
                  );
                })}
              </>
            ) : null}
          </TextContainer>
          <ContentMessageTextarea Editor={Editor} editorRef={commentRef} />
          <PostBtnContainer>
            <PostButton
              Editor={Editor}
              editorRef={commentRef}
              innerText="Post Your Answer"
              handlePost={handlePostAnswer}
            />
          </PostBtnContainer>
        </MainBar>
        <Sidebar />
      </BarContainer>
    </Container>
  );
};

export default Article;

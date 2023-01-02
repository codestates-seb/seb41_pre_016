import { QuestionStore } from "../../../store/zustandQuestion";
import { useCookies } from "react-cookie";
import { userInfoStore } from "../../../store/zustandUserInfo";
import { useNavigate } from "react-router-dom";
import styled from "styled-components";

const BlueBtn = styled.button`
  border: 1px solid hsl(205, 41%, 63%);
  background-color: rgb(10, 149, 255);
  color: white;
  border-radius: 3px;
  box-shadow: inset 0 1px 0 0 hsla(0, 0%, 100%, 0.7);
  padding: 10.4px;
  text-decoration: none;
  white-space: nowrap;
  position: relative;
  margin-left: 4px;
  font-size: 13px;

  :hover {
    background-color: rgb(0, 116, 204);
  }
`;

const PostButton = ({ editorRef, innerText, handlePost }) => {
  const navigate = useNavigate();
  const [cookies, setCookie, removeCookie] = useCookies(["access_jwt"]);
  const { title, tags, postQuestion } = QuestionStore();
  const userInfo = userInfoStore((state) => state.userInfo);
  const postButton = handlePost
    ? handlePost
    : async () => {
        const content = editorRef.current?.getInstance().getHTML();
        const questionObj = {
          title,
          content,
          tags,
        };
        const cookieObj = {
          Authorization: cookies.access_jwt.Authorization,
          Refresh: cookies.access_jwt.Refresh,
        };
        await postQuestion(
          "/question",
          userInfo.userId,
          questionObj,
          cookieObj
        );
        await navigate("/");
      };
  return <BlueBtn onClick={postButton}>{innerText}</BlueBtn>;
};
export default PostButton;

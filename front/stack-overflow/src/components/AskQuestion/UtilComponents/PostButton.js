import { QuestionStore } from "../../../store/zustandQuestion";
import { useCookies } from "react-cookie";
import { userInfoStore } from "../../../store/zustandUserInfo";
import { useNavigate } from "react-router-dom";

const PostButton = ({ Editor, editorRef }) => {
  const navigate = useNavigate();
  const [cookies, setCookie, removeCookie] = useCookies(["access_jwt"]);
  const { title, tags, postQuestion } = QuestionStore();
  const userInfo = userInfoStore((state) => state.userInfo);
  const postButton = async () => {
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
    await postQuestion("/question", userInfo.userId, questionObj, cookieObj);
    await navigate("/");
  };
  return (
    <button onClick={postButton} className="blueButton">
      Post your question
    </button>
  );
};
export default PostButton;

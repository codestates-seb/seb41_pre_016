import { QuestionStore } from "../../../store/zustandQuestion";
import {useCookies} from "react-cookie";

const PostButton = ({Editor,editorRef}) => {
  const [cookies, setCookie, removeCookie] = useCookies(['access_jwt']);
  const { title, tags, postQuestion } = QuestionStore();
  const postButton = () => {
    const content = editorRef.current?.getInstance().getHTML();
    const questionObj = {
      title,
      content,
      tags,
    };
    const cookieObj={
      Authorization:cookies.access_jwt.Authorization,
      Refresh:cookies.access_jwt.Refresh,
    }
    postQuestion("/question", 6, questionObj,cookieObj);
  };
  return <button onClick={postButton} className='blueButton'>Post your question</button>;
};
export default PostButton;

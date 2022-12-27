import { QuestionStore } from "../../../store/zustandQuestion";

const PostButton = () => {
  const { title, content, tags, postQuestion } = QuestionStore();
  const postButton = () => {
    const questionObj = {
      title,
      content,
      tags,
    };
    postQuestion("/question", 8, questionObj);
  };
  return <button onClick={postButton}>Post your question</button>;
};
export default PostButton;

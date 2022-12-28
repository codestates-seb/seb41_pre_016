import { QuestionStore } from "../../../store/zustandQuestion";

const ContentMessageTextarea = () => {
  const { content, setContent } = QuestionStore();
  return (
    <textarea
      id="body"
      name="body"
      type="text"
      value={content}
      onChange={(e) => {
        setContent(e.target.value);
      }}
    ></textarea>
  );
};
export default ContentMessageTextarea;

import { QuestionStore } from "../../../store/zustandQuestion";

const ContentsMessageInput = () => {
  const { title, setTitle } = QuestionStore();
  return (
    <input
      id="title"
      name="title"
      type="text"
      maxLength="300"
      placeholder="e.g. Is there an R function for
              finding the index of an element in a vector?"
      className="s-input js-post-title-field ask-title-field"
      data-min-length="15"
      data-max-length="150"
      value={title}
      onChange={(e) => {
        setTitle(e.target.value);
      }}
    ></input>
  );
};
export default ContentsMessageInput;

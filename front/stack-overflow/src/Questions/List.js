import Summary from "./Contents/Summary";
import useStore from "./Store/SummaryStates";
import { useEffect } from "react";

const List = () => {
  const { questions } = useStore();

  useEffect(() => {
    console.log(questions);
  }, []);
  return (
    <>
      {questions
        ? questions.map((el) => {
            return (
              <Summary
                key={el.id}
                title={el.title}
                content={el.content}
                tags={el.tags}
                vote={el.votes}
                answer={el.answerCount}
                views={el.views}
                bounty={50}
              />
            );
          })
        : null}
    </>
  );
};

export default List;

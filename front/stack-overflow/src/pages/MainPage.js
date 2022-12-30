import LeftSideBar from "../components/LeftSideBar/LeftSideBar";
import useFetch from "../store/useFetch";
import Questions from "./Questions/Questions";

//import for testing
import Article from "./Article/Article";

const MainPage = () => {
  const [data, isPending, error] = useFetch("/user/1");
  return (
    <>
      <LeftSideBar />
      <Questions>
        {error && <div>error</div>}
        {isPending && <div>Loading</div>}
        {data && console.log("HI")}
      </Questions>
    </>
  );
};

export default MainPage;

import LeftSideBar from "../components/LeftSideBar/LeftSideBar";
import useFetch from "../store/useFetch";

//import for testing
import Article from "./Article/Article";
import useStore from "../store/ArticleStates";
import Loading from "../components/Widget/Loading";

const MainPage = () => {
  const [data, isPending, error] = useFetch("/user/1");
  return (
    <>
      <LeftSideBar />
      <Article>
        {error && <div>error</div>}
        {isPending && <div>Loading</div>}
        {data && console.log("HI")}
      </Article>
    </>
  );
};

export default MainPage;

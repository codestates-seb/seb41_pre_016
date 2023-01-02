import LeftSideBar from "../components/LeftSideBar/LeftSideBar";

//import for testing
import Article from "./Article/Article";
import useStore from "../store/ArticleStates";
import Loading from "../components/Widget/Loading";

const MainPage = () => {
  return (
    <>
      <LeftSideBar />
      <Article />
    </>
  );
};

export default MainPage;

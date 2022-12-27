import { useState, useEffect } from "react";
import produce from "immer";

const usePagelist = (page, total) => {
  const [pageList, setPageList] = useState([]);

  useEffect(() => {
    let _size = 0;
    let _page = page >= 3 ? page - 2 : 1;
    const result = [];

    while (_size < 5) {
      if (_page > total) break;
      result.push(_page);
      _page++;
      _size++;
    }

    setPageList(result);
  }, []);

  return { pageList };
};

export default usePagelist;

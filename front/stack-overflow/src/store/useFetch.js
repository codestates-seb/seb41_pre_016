import { useEffect, useState } from "react";
import axios from "axios";

const useFetch = (url) => {
  const [data, setData] = useState(null);
  const [isLoading, setIsLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    const fetch = async () => {
      setIsLoading(true);

      try {
        const response = await axios.get(url);
        console.log("response", response);
        setData(response);
      } catch (e) {
        console.log(e);
        setError(e.message);
      }
      setIsLoading(false);
    };
    fetch();
  }, [url]);
  return [data, isLoading, error];
};
export default useFetch;

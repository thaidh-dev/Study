import {useEffect} from 'react'

// A custom Hook is a JavaScript function whose name starts with ”use” and that may call other Hooks
function useDocumentTitle(count) {
  useEffect(() => {
    document.title = `Count ${count}`
  }, [count])
}

export default useDocumentTitle

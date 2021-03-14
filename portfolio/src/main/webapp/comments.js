/**
 * Fetch comments from the server and appends them to the page
 */
async function addCommentsToPage() {
  const serverResponse = await fetch('/comments');
  const responseJson = await serverResponse.json();
  let commentsContainer = document.querySelector('.comments-container');

  // Append each comment to the page
  responseJson.forEach(comment => {
    commentsContainer.innerHTML +=
        `<p class="comment">"${comment.comment}" - ${comment.author}</p>`;
  });
};

// Execute the fetch once the DOM content is loaded and parsed
document.addEventListener('DOMContentLoaded', addCommentsToPage);

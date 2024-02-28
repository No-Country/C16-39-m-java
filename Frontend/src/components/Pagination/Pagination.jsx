import PropTypes from "prop-types";

const Pagination = ({
  currentPage,
  itemsPerPage,
  totalMovies,
  onPageChange,
}) => {
  const totalPages = Math.ceil(totalMovies / itemsPerPage);
  const pagesToShow = 10;

  const handlePageChange = (page) => {
    onPageChange(page);
  };

  const startPage = Math.max(1, currentPage - Math.floor(pagesToShow / 2));
  const endPage = Math.min(startPage + pagesToShow - 1, totalPages);

  return (
    <div>
      <div className="flex justify-center">
        <button className="mr-2 border-2 border-orange-500  hover:bg-orange-600 transition duration-300 ease-in-out text-white py-2 px-4 rounded"
          disabled={currentPage === 1}
          onClick={() => handlePageChange(currentPage - 1)}
        >
          Previous
        </button>

        {Array.from(
          { length: endPage - startPage + 1 },
          (_, i) => startPage + i
        ).map((page) => (
          <button 
            key={page}
            onClick={() => handlePageChange(page)}
            className={`${
                currentPage === page ? "bg-orange-500 text-white" : "bg-gray-200 text-gray-700"
              } py-2 px-4 rounded mr-2`}
          >
            {page}
          </button>
        ))}

        <button className="ml-1 border-2 border-orange-500  hover:bg-orange-600 transition duration-300 ease-in-out text-white py-2 px-4 rounded"
          disabled={currentPage === totalPages}
          onClick={() => handlePageChange(currentPage + 1)}
        >
          next
        </button>
      </div>
      <span className="flex justify-center">
        Page {currentPage} of {totalMovies}
      </span>
    </div>
  );
};

Pagination.propTypes = {
  currentPage: PropTypes.number,
  itemsPerPage: PropTypes.number,
  totalMovies: PropTypes.number,
  onPageChange: PropTypes.func,
};

export default Pagination;


SELECT unlabelled.*
FROM (
	SELECT a.*
	FROM article a
	LEFT JOIN label l
	ON l.article=a.id

	INNER JOIN config rpa
	ON rpa.id = 'reviews_per_article'
	
	GROUP BY a.id, rpa.int_value
	HAVING COUNT(DISTINCT l.account) < rpa.int_value
) unlabelled

LEFT JOIN label lc
ON lc.article = unlabelled.id
AND lc.account = ?

WHERE lc.article IS NULL
LIMIT 1

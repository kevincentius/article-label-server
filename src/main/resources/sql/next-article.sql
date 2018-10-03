SELECT unlabelled.*
FROM (
	SELECT a.*
	FROM article a
	LEFT JOIN label l
	ON l.article=a.id
	GROUP BY a.id
	HAVING COUNT(DISTINCT l.account) < 10
) unlabelled

LEFT JOIN label lc
ON lc.article = unlabelled.id
AND lc.account = ?

WHERE lc.article IS NULL
LIMIT 1;

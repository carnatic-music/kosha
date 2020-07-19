/* Note: this function depends on three other functions that must be added separately: similarity, levenshtein, difference. */
DROP FUNCTION similarity_score(varchar, varchar);
CREATE OR REPLACE function similarity_score(a varchar, b varchar) RETURNS double precision AS
$BODY$
DECLARE
  score float;
BEGIN
  SELECT (10 * similarity (a, b)) + (1.3 * (10 - levenshtein (a, b))) + (10 * (difference (a, b) /4 ))
  INTO score;
  RETURN score/3.3;
END;
$BODY$
language 'plpgsql';

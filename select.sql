SELECT p.pedidoID AS pedido_id,
     c.id AS cliente_id,
    c.nome AS cliente_nome,
    c.email AS cliente_email,
    p.quantidade,
    pr.id AS produto_id,
    pr.nome AS produto_nome,
    pr.valor AS produto_valor,
    p.valor_total
FROM tb_clientes c
INNER JOIN tb_pedidos p ON c.id = p.client_id
INNER JOIN tb_produtos pr ON p.produto_id = pr.id;
